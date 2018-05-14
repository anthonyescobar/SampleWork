'''
Anthony Escobar

MazeClause.py

Specifies a Propositional Logic Clause formatted specifically
for Grid Maze Pathfinding problems. Clauses are a disjunction of
GridPropositions (2-tuples of (symbol, location)) mapped to
their negated status in the sentence.
'''
import unittest

class MazeClause:

    def __init__ (self, props):
        self.props = {}
        self.valid = False
        if len(props) < 1:
            return
        for p in props:
            if p[0] not in self.props:
                self.props[p[0]] = p[1]
            elif p[1] != self.props[p[0]]:
                self.valid = True
                self.props[p[0]] = None
        # print(self.props)

    def getProp (self, prop):
        if prop not in self.props: return None
        return self.props[prop]

    def isValid (self):
        return self.valid

    def isEmpty (self):
        # if len(self.props) < 1:
        #     return True
        # return False
        return True if len(self.props) == 0 else False

    def contrad (self):
        for p in self.props:
            # print(self.props[p])
            self.props[p] = not self.props[p]
        # print(self.props)

    def __eq__ (self, other):
        return self.props == other.props and self.valid == other.valid

    def __hash__ (self):
        # Hashes an immutable set of the stored props for ease of
        # lookup in a set
        return hash(frozenset(self.props.items()))

    # Hint: Specify a __str__ method for ease of debugging (this
    # will allow you to "print" a MazeClause directly to inspect
    # its composite literals)
    def __str__ (self):
        s = "["
        for p in self.props:
            s = s + str(p) + ", " + str(self.props[p]) + "), "
        s = s[:-2] + "]"
        return s

    @staticmethod
    def resolve (c1, c2):
        results = set()
        counter = 0
        if c1.isValid() or c2.isValid(): return results
        if c1 == c2: return results
        res = []
        for p1 in c1.props:
            # print("RESOLVING")
            # print(p1)
            if c2.getProp(p1) == None:
                # print("NOTIN")
                res.append((p1,c1.getProp(p1)))
            elif c2.getProp(p1) == c1.getProp(p1):
                # print("SHARED")
                res.append((p1,c1.getProp(p1)))
            elif c2.getProp(p1) != c1.getProp(p1):
                # print("RESO")
                counter += 1
        for p2 in c2.props:
            # print(p2)
            if c1.getProp(p2) == None and p2 not in res:
                # print("ADDED")
                res.append((p2,c2.getProp(p2)))
        # print(counter)
        if counter > 1: return results
        if counter == 1 and len(res) == 0: return {MazeClause([])}
        results.add(MazeClause(res))
        return results


class MazeClauseTests(unittest.TestCase):
    def test_mazeprops1(self):
        # print("\ntesting1")
        mc = MazeClause([(("X", (1, 1)), True), (("X", (2, 1)), True), (("Y", (1, 2)), False)])
        # print(mc) # string test
        self.assertTrue(mc.getProp(("X", (1, 1))))
        self.assertTrue(mc.getProp(("X", (2, 1))))
        self.assertFalse(mc.getProp(("Y", (1, 2))))
        self.assertTrue(mc.getProp(("X", (2, 2))) is None)
        self.assertFalse(mc.isEmpty())

    def test_mazeprops2(self):
        # print("\ntesting2")
        mc = MazeClause([(("X", (1, 1)), True), (("X", (1, 1)), True)])
        self.assertTrue(mc.getProp(("X", (1, 1))))
        self.assertFalse(mc.isEmpty())

    def test_mazeprops3(self):
        # print("\ntesting3")
        mc = MazeClause([(("X", (1, 1)), True), (("Y", (2, 1)), True), (("X", (1, 1)), False)])
        self.assertTrue(mc.isValid())
        self.assertTrue(mc.getProp(("X", (1, 1))) is None)
        self.assertFalse(mc.isEmpty())

    def test_mazeprops4(self):
        # print("\ntesting4")
        mc = MazeClause([])
        self.assertFalse(mc.isValid())
        self.assertTrue(mc.isEmpty())

    def test_mazeprops5(self):
        # print("\ntesting5")
        mc1 = MazeClause([(("X", (1, 1)), True)])
        mc2 = MazeClause([(("X", (1, 1)), True)])
        res = MazeClause.resolve(mc1, mc2)
        self.assertEqual(len(res), 0)

    def test_mazeprops6(self):
        # print("\ntesting6")
        mc1 = MazeClause([(("X", (1, 1)), True)])
        mc2 = MazeClause([(("X", (1, 1)), False)])
        res = MazeClause.resolve(mc1, mc2)
        self.assertEqual(len(res), 1)
        self.assertTrue(MazeClause([]) in res)
        print(res)

    def test_mazeprops7(self):
        # print("\ntesting7")
        mc1 = MazeClause([(("X", (1, 1)), True), (("Y", (1, 1)), True)])
        mc2 = MazeClause([(("X", (1, 1)), False), (("Y", (2, 2)), True)])
        res = MazeClause.resolve(mc1, mc2)
        self.assertEqual(len(res), 1)
        self.assertTrue(MazeClause([(("Y", (1, 1)), True), (("Y", (2, 2)), True)]) in res)

    def test_mazeprops8(self):
        # print("\ntesting8")
        mc1 = MazeClause([(("X", (1, 1)), True), (("Y", (1, 1)), False)])
        mc2 = MazeClause([(("X", (1, 1)), False), (("Y", (1, 1)), True)])
        res = MazeClause.resolve(mc1, mc2)
        self.assertEqual(len(res), 0)

    def test_mazeprops9(self):
        # print("\ntesting9")
        mc1 = MazeClause([(("X", (1, 1)), True), (("Y", (1, 1)), False), (("Z", (1, 1)), True)])
        mc2 = MazeClause([(("X", (1, 1)), False), (("Y", (1, 1)), True), (("W", (1, 1)), False)])
        res = MazeClause.resolve(mc1, mc2)
        self.assertEqual(len(res), 0)
        print(res == MazeClause([]))

    def test_mazeprops10(self):
        # print("\ntesting10")
        mc1 = MazeClause([(("X", (1, 1)), True), (("Y", (1, 1)), False), (("Z", (1, 1)), True)])
        mc2 = MazeClause([(("X", (1, 1)), False), (("Y", (1, 1)), False), (("W", (1, 1)), False)])
        res = MazeClause.resolve(mc1, mc2)
        self.assertEqual(len(res), 1)
        self.assertTrue(MazeClause([(("Y", (1, 1)), False), (("Z", (1, 1)), True), (("W", (1, 1)), False)]) in res)

if __name__ == "__main__":
    unittest.main()
