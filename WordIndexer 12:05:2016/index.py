# Do not import anything other than syand re!
import sys
import re

# this function removes punctuation from a string.
# you should use this before adding a word to the index,
# so that "asdf." and "asdf" and "asdf," are considered to be
# the same word.

def remove_punctuation(s):
  return re.sub(r'[^\w\s]', '', s)

assert(remove_punctuation('asdf.') == 'asdf')
assert(remove_punctuation('asdf,') == 'asdf')
assert(remove_punctuation("?;'.!") == '')



# index is a global dictionary. The keys should be words, and the
# values should be tuples of (filename, line number, position in line).

index = {}


def build_index():
  global index
  args = sys.argv[1:]
  args.sort()
  for file in args:
    ln = 0
    with open(file,'r') as f:
      for line in f:
        ln += 1
        pos = 0
        for word in line.split():
          tup = (file,ln,pos)
          pos += len(word) +1
          if remove_punctuation(word.lower().strip()) in index:
            index[remove_punctuation(word.lower().strip())].append(tup)
          else:
            index[remove_punctuation(word.lower().strip())] = [tup]
  return index
build_index()


# commands

def words(args):
  arr = [x for x in index.keys() if x.startswith(args[0].lower())]
  arr.sort()
  for s in arr:
    print(s)

def occurrences(args):
  arr = index.get(args)
  i = 0
  for s in arr:
    print("  ("+str(i)+") File " + str(arr[i][0]) + ", Line " + str(arr[i][1]) + ", Character " + str(arr[i][2]))
    i += 1
  arr = []

def output(args):
  arr = list(index.keys())
  arr.sort()
  for s in arr:
    print(s)
    occurrences(s)

cmds = {
  'words' : words,
  'occurrences' : occurrences,
  'output' : output,
  }

def interact():
  # print the prompt line
  print('> ', end='', flush=True)
  
  for ln in sys.stdin:
    ln = ln.strip().split(' ')
    if ln[0] == 'quit':
      return
    else:
      cmds[ln[0]](ln[1:])

    # print the next prompt line
    print('> ', end='', flush=True)

interact()
