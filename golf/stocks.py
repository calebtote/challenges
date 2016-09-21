#!/usr/bin/python
#stocks.py

'''
Given an array of indices which correlate to minutes past trade opening time and 
 the values are the price of the stock at that time

Determine the maximum profit from exactly 1 buy and 1 sell
'''

def M(s):
 n=s[0]
 m=s[1]-n
 for i in s[1:]:
  if i<n:n=i
  else:m=max(m,i-n)
 return max(m,s[-1]-s[-2])

stockList = []
stockList.append([1,2,3,4,5])
stockList.append([1,99,2,105])
stockList.append([99,1,99,100])
stockList.append([99,1,1,2,1,3])
stockList.append([5,4,3,3,1])
stockList.append([5,4,3,1])
stockList.append([5,2,1])
stockList.append([5,4,1])
stockList.append([5,1])
stockList.append([10,7,5,1])
for stocks in stockList:
    print M(stocks)