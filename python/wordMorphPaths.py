#prints the ways to change one word to another by changing one letter at a time
input = "cat"
output = "lop"
alphabet = list("abcdefghijklmnopqrstuvwxyz")
allowedwords = {"lap","lat","mat","hat","her","hee","hoo","lop","lot"}
#should print lop<--lot<--lat<--cat and lop<--lap<--lat<--cat
allwords = set()
nodeQueue = []
class wordNode:
    def __init__(self,word,parentNode):
        self.word = word
        self.parent = parentNode
        allwords.add(word)
def getNewWords(input):
    newWords = []
    for i in range(0,len(input)):
        for n in range (0,len(alphabet)):
            newWord  = input
            newWord = list(newWord)
            newWord[i] = alphabet[n]
            newWord = ''.join(newWord)
            if newWord in allowedwords:
                newWords.append(newWord)
    return newWords
def discoverWords(wordNode1):
    newWords = getNewWords(wordNode1.word)
    if output in newWords:
        return printPathToOutput(wordNode1)
    else:
        for word in newWords:
            if word not in allwords:
                p = wordNode(word,wordNode1)
                nodeQueue.insert(0,p)
def printPathToOutput(wordNode):
    parent = wordNode.parent
    outString = output
    while parent is not None:
        outString = outString + "<--" + wordNode.word
        wordNode = wordNode.parent
        parent = wordNode.parent
    outString += "<--" + input
    print outString
nodeQueue.append(wordNode(input, None))
while len(nodeQueue)>0:
    discoverWords(nodeQueue.pop())





