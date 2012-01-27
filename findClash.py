import sys

clashes = {}

for line in open('dirs.list','r').readlines():
    elems = line.strip().split('~')
    
    code = elems[4]

    if code != 'DIR':
        if code in clashes:
            print "CLASH",line.strip()
        else:
            clashes[code] = 1
