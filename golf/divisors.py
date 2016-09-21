#!/usr/bin/python
#divisors.py

'''
Find sum of divisors, for all N in a given list
'''

import time

def DoWork(nums):
    sums = []
    for n in nums:
        divisors = [x for x in range(1,n+1) if not n % x]
        #divisors.append(n)
        sums.append(sum(divisors))
    return sums

def DoWork_old(nums):
    sums = []
    for n in nums:
        divisors = []
        for i in range(1,n+1):
            if not (n%i):
                divisors.append(i)
        sums.append(sum(divisors))
    return sums

def DoWork_new(nums):
    return map(lambda n: sum(i for i in range(1, n+1) if not n % i), nums)

t0 = time.clock()
for i in range(10):
    result = DoWork(range(1,9999))
t = time.clock() - t0
print result
print t/10, 'seconds (DoWork)'