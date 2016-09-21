#!/usr/bin/python
#bogosort.py

'''
Silly challenge to come up with the most inefficient sorting algorithm
'''

import random

def sort(datum):
    tries = 0
    while not in_order(datum):
        random.shuffle(datum)
        tries += 1
    print tries
    return datum
 
def in_order(datum):
    last = datum[0]
    for num in datum[1:]:  # from index 1 to n
        if num < last:
            return False
        last = num
    return True

datum = [0,1,3,2,5,4,7,6,8,9,10]
datum = sort(datum)
print datum