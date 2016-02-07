#a list based heap implementation
heap = [None]
def add((key,value)):
    heap.append((key,value))
    bubbleUp(len(heap)-1)
def bubbleUp(index):
    if index > 1:
        if heap[index][0] < heap[getParentIndex(index)][0]:
            temp = heap[index]
            heap[index] = heap[getParentIndex(index)]
            heap[getParentIndex(index)] = temp
            bubbleUp(getParentIndex(index))
def getParentIndex(index):
    return index/2
def printHeap():
    i = 1
    first = 1
    multiple = 1
    while i < len(heap):
        if i+1 == first + multiple:
            multiple *= 2
            print heap[first:i+1]
            first = i +1
        i+=1
    if heap[first:] is not []:
        print heap[first:]
def remove(index):
    assert index < len(heap)
    temp = heap[index]
    if len(heap) > 2:
        heap[index] = heap.pop()
        bubbleDown(index)
    else:
        heap.pop()
    return temp
def bubbleDown(index):
    if index*2 + 1 < len(heap):
        left = heap[index*2]
        right = heap[index*2+1]
        if left[0] <= right[0]:
            if left[0] < heap[index][0]:
                heap[index * 2] = heap[index]
                heap[index] = left
                bubbleDown(index*2)
        elif left[0] > right[0]:
            if right[0] < heap[index][0]:
                heap[index*2+1] = heap[index]
                heap[index] = right
                bubbleDown(index*2+1)
    elif index*2 < len(heap):
        if heap[index*2][0] < heap[index][0]:
                temp = heap[index]
                heap[index] = heap[index*2]
                heap[index*2] = temp

