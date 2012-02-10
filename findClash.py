import sys

clashes = {}

for line in open('dirs.list','r').readlines():
    elems = line.strip().split('~')
    
    code = elems[4]
    size = int(elems[1])

    if code != 'DIR' and size != 0:
        if code in clashes:
            print "CLASH",line.strip(),"WITH",clashes[code]
        else:
            clashes[code] = line.strip()
