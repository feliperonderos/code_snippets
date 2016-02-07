#A queue implementation with a fixed max size
class Queue:
    def __init__(self, maxSize):
        self.size = 0;
        self.maxSize = maxSize
        self.data = [None] * maxSize
        self.head = 0
        self.tail = 0
    def enqueue(self, data):
        if not self.isFull():
            self.data[self.tail] = data
            self.size += 1
            self.tail = (self.tail+1) % self.maxSize
        return False
    def dequeue(self):
        if not self.isEmpty():
            temp = self.data[self.head]
            self.data[self.head] = None
            self.size -= 1
            self.head = (self.head + 1) % self.maxSize
            return temp
        else:
            return False
    def isFull(self):
        return self.size == self.maxSize
    def isEmpty(self):
        return self.size < 1
    def printOut(self):
        print(self.data)

