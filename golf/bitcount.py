#!/usr/bin/python
#bitcount.py

'''
Given a non negative integer number `num`.
For every number `i` in the range 0 <= i <= num, calculate the number of 1's in their
 binary representation and return them as an array.
'''

def countBits(num):
    index = 1
    base = [0]

    base.append(map(lambda index: base[(index & (index -1))]+1, num))
    #while(index <= num):
    #    base.append(base[(index & (index - 1))]+1)
    #    index += 1

    print base
    return base

############################################
# How does it work?!
# Examples:
#   {1} --
#       bin(1)              :   1
#       bin(1-1)            :   0
#       1 & 0               :   0 (0)
#       append(base[0]+1)   :   append(1)
#     //end 1
#
# base => [0,1]
# index => 2
#
#   {2} --
#       bin(2)              :   10
#       bin(2-1)            :   01
#       10 & 01             :   00 (0)
#       append(base[0]+1)   :   append(1)
#     //end 2
#
# base => [0,1,1]
# index => 3
#
#   {3} --
#       bin(3)              :   11
#       bin(3-1)            :   10
#       11 & 10             :   10 (2)
#       append(base[2]+1)   :   append(2)
#     //end 3
#
# base => [0,1,1,2]
#
# ...
#
# base => [0,1,1,2,1,2,2]
# index => 7
#
#   {7} --
#       bin(7)              :   111
#       bin(7-1)            :   110
#       111 & 110           :   110 (6)
#       append(base[6]+1)   :   append(3)
#     //end 7
#
# base => [0,1,1,2,1,2,2,3]
#############################################