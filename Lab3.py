from itertools import chain, combinations

def greedyNum(arr, k):
    i = 0
    p = 0 ##passenger index
    g = 0 ##grab index
    pick = 0 ##count output
    pas = [] ##passenger
    grab = [] ##grab

    ##find P,G and put in list sepertately
    while i < len(arr): 
        if arr[i] == 'G':
            grab.append(i)
        elif arr[i] == 'P':
            pas.append(i)
        i += 1

    ##find whether grab can pick up passenger 
    while p < len(pas) and g < len(grab):
        if (abs(pas[p] - grab[g]) <= k): #grab and passenger are not too far from each other
            pick += 1 #can pick
            p += 1
            g += 1

        elif pas[p] < grab[g]:
            p += 1
        else:
            g += 1
    
    return pick

##find which passengers that grab can pick up
def canPick(arr,k):
    pickList = {}
    for i in range(len(arr)):
        if(arr[i] == 'G'):
            pickList[i] = []
            j = i - k ##check how far grab can go
            #print(j)
            if(j < 0): 
                j = 0
            while(j <= i + k):
                #print(j)
                if not(j < len(arr)): break
                if(i == j): 
                    j += 1
                    continue
                if(arr[j] == 'P'):
                    pickList[i].append(j)
                    #print(pickList)
                j += 1
    #print(pickList)
    return pickList

##pas grab with passenger
def paring(checkPick):
    pair = {}
    for i in list(checkPick):
        pair[i] = []
        if(len(checkPick[i]) > 0):
            for j in checkPick[i]:
                tmp = (i,j)
                #print(tmp)
                pair[i].append(tmp)
    #print(len(pair))
    return pair

def cartesian(arr):
    result = [[]]
    for a in arr:
        result = [x+[y] for x in result for y in a]
    #print(len(result))
    return result

##to delete duplicate passengers
def powerset(arr):
    #print(len(list(chain.from_iterable(combinations(arr, g) for g in range(len(arr))))))
    return list(chain.from_iterable(combinations(arr, g) for g in range(len(arr)+1)))

##generate all possibility
def generateAll(pas):
    p = [pas[x] for x in list(pas)]
    allTmp = cartesian(p)
    all = {}
    tmp = []
    for i in allTmp:
        pas = [x[1] for x in i]
        q = set(pas)
        if(len(pas) != len(q)): 
            dup = {}
            for x in q: 
                dup[x] = pas.count(x)
            ps = powerset(i)
            ps2 = [list(x) for x in ps]
            for y in list(dup):
                if(dup[y] > 1):
                    for psx in ps2:
                        if(len(psx) == (len(list(pas)) - dup[y]+1)):
                            pp = [x[1] for x in psx]
                            qq = set(pp)
                            if(len(pp) == len(qq)): 
                                tmp.append(psx)
                                #print("1")

        else:
            tmp.append(i)
    #print(cartesian(pas))
    #print(len(pas))
    #print(len(powerset(tmp)-1))
    #tmp.remove([])
    tmp2 = []
    for i in tmp:
        if(i not in tmp2): tmp2.append(i)
    for i in tmp2:
        if(len(i) not in all): all[len(i)] = []
        all[len(i)].append(i)
    #print(powerset(cartesian(all)))
    #print("BF num :", len(powerset(all))-1,"\n")
    #print(powerset(all))
    #print(len(powerset(tmp2))-1)
    #print(all)
    return all


def bruteForceNum(arr,k):
    checkPick = canPick(arr,k)
    pas = paring(checkPick)
    allTmp = generateAll(pas)
    # print(checkPick)
    #print(len(pas))
    #print(len(allTmp))
    #print(len(powerset(arr)))
    return max(list(allTmp))

#print(powerset([1,2,3,4]))
print("\n")
arr = input("Input : arr[] = ")
print("\n----------------------------------------\n")
arr1 = []
for i in arr:
    arr1.append(i)
k = int(input("            k = "))
print("\n----------------------------------------\n")
r1 = bruteForceNum(arr1, k)
print("BF Output :",r1)
print("\n----------------------------------------\n")
r2 = greedyNum(arr1, k)
print("GD Output :",r2)
print("\n")
