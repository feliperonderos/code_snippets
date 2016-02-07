#here is the backend for my personal website. It is based upon the webapp2 platform for Google app engine for Python.
import webapp2
import os
import jinja2
from datetime import datetime
from google.appengine.ext import db
from google.appengine.api import memcache
from google.appengine.api import mail
import logging
template_dir = os.path.join(os.path.dirname(__file__), 'templates')
jinja_env = jinja2.Environment(loader = jinja2.FileSystemLoader(template_dir),
                               autoescape = True)

def render_str(template, **params):
    t = jinja_env.get_template(template)
    return t.render(params)

class BaseHandler(webapp2.RequestHandler):
    def render(self, template, **kw):
        self.response.out.write(self.render_str(template, **kw))

    def write(self, *a, **kw):
        self.response.out.write(*a, **kw)

    def render_str(self, template, **params):
        return render_str(template, **params)

    def initialize(self, *a, **kw):
        webapp2.RequestHandler.initialize(self, *a, **kw)
class Main(BaseHandler):
    def get(self):
       self.render('main.html')
class Contact(BaseHandler):
    def get(self):
        self.render('contact.html')
    def post(self):
        sender = self.request.get('sender')
        message = self.request.get('message')
        email = self.request.get('email')
        if (sender == "") or (message == "") or (email == ""):
            self.render('contact.html',not_filled=True)
            return
        m = Message(
            sender = sender,
            message = message,
            email = email
            )
        m.put()
        m.created_epoch = int((datetime.now()- datetime(1970,1,1)).total_seconds())
        m.ID = m.key().id()
        m.put()
        mail.send_mail(sender='whenstuffattacks@gmail.com',
              to='feliperonderos@gmail.com',
              subject=email+" "+sender,
              body=message)
        memcache.set(str(sender),m)
        self.response.headers.add_header(
            'Set-Cookie',
            '%s=%s; Path=/' % ('sender',str(sender)))
        self.redirect('/messages/'+sender)
class Message(db.Model):
    sender = db.StringProperty(required = True)
    message = db.StringProperty(required = True,multiline=True)
    email = db.StringProperty(required = True)
    visible = db.BooleanProperty(default = True)
    ID = db.IntegerProperty()
    created = db.DateTimeProperty(verbose_name=None, auto_now=False, auto_now_add=True)
    created_epoch = db.IntegerProperty(default = 0)
class Messages(BaseHandler):
    def get(self,sender=""):
        if sender=="":
            sender = self.request.cookies.get('sender')
        n = True
        q = db.Query(Message)
        q.filter("sender =", str(sender))
        q.filter("visible =", True)
        q.order('-created_epoch')
        q = q.fetch(limit=None)
        p = memcache.get(str(sender))
        for e in q:
            if (e is not None) and (p is not None) and (e.message == p.message):
                n = False
        if n and p is not None:
            q.append(p)
        self.render("messages.html",messages=q,sender=sender)
    def post(self,sender=""):
        logging.info(self.request.body)
        logging.info(sender)
        memcache.flush_all()
        m = Message.get_by_id(int(self.request.body))
        m.visible = False
        m.put();
        self.redirect(self.request.url)
class Projects(BaseHandler):  
    def get(self):
        self.render('projects.html')
app = webapp2.WSGIApplication([
    ('/', Main),
    ('/contact',Contact),
    ('/projects',Projects),
    ('/messages/(.+)',Messages),
    ('/messages/',Messages)
], debug=False)