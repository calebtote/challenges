def getMax(stocks):
    curMax = stocks[1]-stocks[0]
    curMaxb = 0
    m1 = stocks[0]
    for i in stocks[1:]:
        print 'i',i
        print 'curM', curMax
        print 'curMb', curMaxb
        if i < m1:
            m1 = i
            print 'm1:',m1
            #if i != stocks[-1]: continue
        else: curMax = max(curMax,i-m1)
        #if curMax < i-m1:
        #    curMaxb = curMax
        #    curMax = i-m1
        #    print 'cMax updated:',curMax
        #    continue
        if i==stocks[-1]:
            print 'cMax updated:',curMax
            curMax = max(curMax,i-stocks[-2])

    print 'final cmax', curMax
    print '\n\n\n'

def getMaxb(stocks):
    curMax=0
    m = min(stocks)


stockList = []
stockList.append([1,2,3,4,5])
stockList.append([1,99,2,105])
stockList.append([99,1,99,100])
stockList.append([99,1,1,2,1,3])
stockList.append([5,4,3,3,1])
stockList.append([5,4,3,1])
stockList.append([5,2,1])
for stocks in stockList:
    getMax(stocks)