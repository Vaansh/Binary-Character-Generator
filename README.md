# Binary Character Generator

## Description

A program planned in pseudo code and executed in Java of two adaptations of a program that takes an input number of any length of irregular number of binary characters `0` and `1` and a concealed `*` character at certain positions, locates all potential groupings of paired strings that can be built by supplanting the masked `*` character by one or the other 0 or 1.

### Folder structure

Here's the complete folder structure of the project:
```
|-- Binary Character Generator
    |-- .gitignore
    |-- README.md
    |-- complexityV1.txt
    |-- complexityV2.txt
    |-- outV1.txt
    |-- outV2.txt
    |-- pseudocodeV1.txt
    |-- pseudocodeV2.txt
    |-- version1executable.jar
    |-- version2executable.jar
    |-- Version 1
    |   |-- doc
    |   |-- src
    |       |-- RevealStr.java
    |-- Version 2
        |-- doc
        |-- src
            |-- RevealStr.java
```

## Pseudocode

### Recursive (Version 1)
```
Algorithm RevealStr(str, len) 
INPUT string str of length len
DECLARE ind, lind, str1, str2, op1, op2

ind←str.str.indexOf('*')
lind←str.str.lastIndexOf('*')

{if first and last index are same, perform the operation one time and display output}
IF ind=lind
	str1←str.substring(0, ind)
	str2←str.substring(ind+1, len)
	op1←str1 + "0" + str2
	op2←str1 + "1" + str2
	OUTPUT op1
	OUTPUT op2
ELSE
	str1←str.substring(0, ind)
	str2←str.substring(ind+1, len)
	op1←str1 + "0" + str2
	op2←str1 + "1" + str2
	RevealStr(op1, len)
	RevealStr(op2, len)
ENDIF
```

### Iterative (Version 2)
```
Algorithm RevealStr(str)
INPUT string str

{count number of masks}
DECLARE n
n←0
FOR a←0 TO str.length DO
	IF str.charAt(a)=*
		n←n+1
	ENDIF
	a←a+1
ENDFOR

{store indexes of masks in string}
DECLARE indexes[n]
ind←0
FOR a←0 TO str.length DO
	IF str.charAt(a)=*
		indexes[ind]←a
		ind←ind+1
	ind←ind+1

{store 0s and 1s in 2d array}
DECLARE t, p, i, power, twod[n][Math.pow(2,n)]
t←0
p←0
i=t
power←0
FOR j←0 TO n-1 DO
	WHILE i<Math.pow(2,n)
		FOR i←t TO Math.pow(2, power) DO			
			IF i<Math.pow(2,n)
				twod[i][j]="0"
				p←p+!
				i←i+1
			ENDIF
		ENDFOR
		t←2*p
	t←0
	p←0
	i←t
	power←power+1
	ENDWHILE
	j←j+1
ENDFOR
FOR e←0 TO n-1 DO
	FOR f←0 to Math.pow(2,n)-1
		IF twod[e][f]=null
			twod[e][f]="1"
		ENDIF
		f←f+1
	ENDFOR
	e←e+1
ENDFOR
         
{rearrange 2d arrays to form a row-wise 1d array}                                
DECLARE store, binary[Math.pow(2,n)]
Store←""
FOR w←0 TO Math.pow(2,n)-1 DO
	FOR v←0 TO n-1 DO
		store ← store + twod[v][w]		
		v←v+1
	ENDFOR
	binary[w]=store
	store←""
	w←w+1
ENDFOR

{display output in console}
DECLARE output
output←str
FOR y←0 TO binary.length-1 DO
	output=str
	FOR z←0 TO indexes.length-1 DO
		output←output.substring(0, indexes[z]) + binary[y].charAt[z] +
                output.substring(indexes[z]+q, output.length)
		z←z+1
		OUTPUT output
	y←y+1
```

## Output

### Recursive (Version 1)
```
Tested for 2 number of masks (*)...
Time taken (in milliseconds): 2.0

Tested for 4 number of masks (*)...
Time taken (in milliseconds): 0.0

Tested for 6 number of masks (*)...
Time taken (in milliseconds): 2.0

Tested for 8 number of masks (*)...
Time taken (in milliseconds): 4.0

Tested for 10 number of masks (*)...
Time taken (in milliseconds): 16.0

Tested for 12 number of masks (*)...
Time taken (in milliseconds): 22.0

Tested for 14 number of masks (*)...
Time taken (in milliseconds): 81.0

Tested for 16 number of masks (*)...
Time taken (in milliseconds): 266.0

Tested for 18 number of masks (*)...
Time taken (in milliseconds): 762.0

Tested for 20 number of masks (*)...
Time taken (in milliseconds): 2976.0

Tested for 22 number of masks (*)...
Time taken (in milliseconds): 10945.0

Tested for 24 number of masks (*)...
Time taken (in milliseconds): 63742.0
```

### Iterative (Version 2)
```
Tested for 2 number of masks (*)...
Time taken (in milliseconds): 3.0

Tested for 4 number of masks (*)...
Time taken (in milliseconds): 1.0

Tested for 6 number of masks (*)...
Time taken (in milliseconds): 3.0

Tested for 8 number of masks (*)...
Time taken (in milliseconds): 12.0

Tested for 10 number of masks (*)...
Time taken (in milliseconds): 21.0

Tested for 12 number of masks (*)...
Time taken (in milliseconds): 73.0

Tested for 14 number of masks (*)...
Time taken (in milliseconds): 183.0

Tested for 16 number of masks (*)...
Time taken (in milliseconds): 717.0

Tested for 18 number of masks (*)...
Time taken (in milliseconds): 1941.0

Tested for 20 number of masks (*)...
Time taken (in milliseconds): 6058.0

Tested for 22 number of masks (*)...
Time taken (in milliseconds): 37324.0

Tested for 24 number of masks (*)...
Time taken (in milliseconds): 156619.0
```

## Complexities

### Recursive (Version 1)
Time complexity of Version 1 is:  O(2^n)
Space complexity of Version 1 is: O(n)

The function comprises of two parameters and makes two recursive calls. 
This means it is not scalable as the function 2^n grows exponentially. 
Thus, at large numbers this method takes longer time.

### Iterative (Version 2)
Time complexity of Version 2 is: O(n^3)
Space complexity of Version 2 is: O(n^2)

The iterative solution has only primitive operations inside all loops, nested or not. The algorithm consists of two for loops (2n), followed by
a for loop nested inside a while loop which in turn is inside another for loop (n^3), after which there are another two nested for loops (2n^2). 
Finally, the output is displayed using a nested for loop (n^2). 

Therefore, the overall complexity is 2n + n^3 + 2n^2 + n^2 = O(n^3 + 3n^2 + 2n), or simply O(n^3). Arrays hold 4 bytes of n.
The solution uses 14 variables (14), 2 1d arrays (2n) and 1 2d array (1n^2), which gives it a space complexity of O(n^2 + 2n + 14), or O(n^2).

## Conclusion
The purpose of this program was to come up with a solution recursively and iteratively, then compare their complexities. We find that the overall time complexity of version 1 is worse than version 2 in the long run since it grows exponentially. 
