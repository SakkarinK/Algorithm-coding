import timeit

# FindGCD1(m,n) ไล่เช็คตั้งแต่ t ลงมาทีละตัว

def findGCD1(m,n):
    m = abs(m)
    n = abs(n)
    if m == 0:
        m = n
    if n == 0:
        n = m
    if n == 0 and m == 0:
        return 0
    t = min(m, n)
    cnt = 0
    while m%t != 0 or n%t != 0:
        cnt += 1
        t -= 1
    return t

 # แยกตัวประกอบเก็บเป็น list
 
def factorization(n):
    p = 2
    f = []
    while n >= p**2:
        if n % p == 0:
            f.append(p)
            n = int(n/p)
        else:
            p += 1
    f.append(n)
    return f

# FindGCD2(m,n) แยกตัวประกอบ m และ n แล้วเอาตัว common มา product กัน

def findGCD2(m,n):
    m = abs(m)
    n = abs(n)
    if m == 0:
        m = n
    if n == 0:
        n = m
    t = 1
    lm = factorization(m)       # n
    ln = factorization(n)       # n
    pre = 0
    for num in lm:              # n
        if num in ln and num != pre:
            pre = num
            a = lm.count(num)
            b = ln.count(num)
            t *= num**min(a, b)
    return t



# Recursive

def findGCD3(m,n):
    m = abs(m)
    n = abs(n)
    if m == 0:
        m = n
    if n == 0:
        n = n
    if m > n:
        if m%n == 0:
            return n
        return findGCD3(m - n, n)
    elif m == n:
        return m
    else:
        if n%m == 0:
            return m
        return findGCD3(m, n - m)  

# Run กับตัวเลขมากๆได้ดี

def FindGCD3(m,n):
    m = abs(m)
    n = abs(n)
    if m == 0:
        m = n
    if n == 0:
        n = m
    while (m/n > 100):
        m = m - (100*n)
    while (n/m > 100):
        n=n - (100*m)
    if m > n:
        return FindGCD3(m-n,n)
    elif m == n:
        return m
    else:
        return FindGCD3(m,n-m)




start1 = timeit.default_timer()
x = findGCD1(findGCD1(36,84),120)
end1 = (timeit.default_timer() - start1)*10e6

start2 = timeit.default_timer()
y = findGCD2(742271756, 60622886)
end2 = (timeit.default_timer() - start2)*10e6

start3 = timeit.default_timer()
z = findGCD3(742271756, 60622886)
end3 = (timeit.default_timer() - start3)*10e6

start4 = timeit.default_timer()
w = FindGCD3(742271756, 60622886)
end4 = (timeit.default_timer() - start4)*10e6

print("findGCD1:", x)
print("findGCD1 Runtime:", end1,"µs\n")

print("findGCD2:", y)
print("findGCD2 Runtime:", end2,"µs\n")

print("findGCD3:", z)
print("findGCD3 Runtime:", end3,"µs\n")

print("FindGCD3:", w)
print("FindGCD3 Runtime:", end4,"µs\n")