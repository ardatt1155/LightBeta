
/*
Today we will play around with trees.

How do you represent a generic n-ary tree.
Given a Tree, define the post-order traversal.
Write a recursive function to print this traversal. What is the space/time complexity of this algorithm?
What is a balanced tree? How would you generalize the notion of a balanced-binary-tree to a n-ary tree.
What is the space/time complexity of a recursive algorithm for a balanced tree?
How can we improve the space complexity to O(1)?
Write an iterative function to print the post-order of a generic tree.

Given a pre-order-traversal and post-order-traversal for a generic tree, can you resurrect the tree?

*/


#include <iostream>
#include <vector>
#include <cassert>
using namespace std;

static int DEBUG = 0;

class Tree {
  public:
    int Key;
    Tree *Master;
	  Tree *Slave;
    Tree *Peer;

    Tree() {
      this->Key = -1;
      this->Master = this->Slave = this->Peer = 0x0;
    }
};

static int POTR(Tree *R, vector<int> &V)
{
	if (R == NULL) return 0;
	if (R->Slave) POTR(R->Slave, V);
  V.push_back(R->Key);
	if (R->Peer) POTR(R->Peer, V);
	return 0;
}

static int POTI(Tree *R, vector<int> &V)
{
  while (R) {
    if (R->Slave) {
      R = R->Slave;
      continue;
    }
    V.push_back(R->Key);
    if (R->Peer) {
      R = R->Peer;
      continue;
    }
    while (R->Master != NULL) {
      R = R->Master;
      V.push_back(R->Key);
      if (R->Peer != NULL) break;
    }
    R = R->Peer ? R->Peer : R->Master;
  }
  return 0;
}

static Tree* Resurrect(
  pair<vector<int>::iterator, vector<int>::iterator> R1, //post-order
  pair<vector<int>::iterator, vector<int>::iterator> R2) //pre-order
{
  assert(*(R1.second) == *(R2.first));
  Tree *l = 0x0, *T = new Tree();
  T->Key = *(R1.second);
  vector<int>::iterator P1 = R1.first, P2 = R1.first, Q1 = R2.first, Q2 = R2.first;
  Q1++, Q2++;
  while (*(P2) != *(R1.second)) {
    while (*P2 != *Q1) P2++, Q2++;
    Tree *s = Resurrect(pair<vector<int>::iterator, vector<int>::iterator>(P1, P2), pair<vector<int>::iterator, vector<int>::iterator>(Q1, Q2));
    if (T->Slave == NULL) T->Slave = s, l = s; else l->Peer = s, l = l->Peer;
    P1 = P2 + 1, Q1 = Q2 + 1, P2 = P1, Q2 = Q1;
  }
  return T;
}

int main()
{
  Tree T1, T2, T3, T4, T5, T6, T7, T8, T9; vector<int> V;
  T1.Key = 1, T1.Peer = &T2, T1.Slave = 0x0, T1.Master = &T3;
  T2.Key = 2, T2.Peer = 0x0, T2.Slave = 0x0, T2.Master = &T3;
  T3.Key = 3, T3.Peer = 0x0, T3.Slave = &T1, T3.Master = &T4;
  T4.Key = 4, T4.Peer = &T7, T4.Slave = &T3, T4.Master = &T9;
  T5.Key = 5, T5.Peer = &T6, T5.Slave = 0x0, T5.Master = &T7;
  T6.Key = 6, T6.Peer = 0x0, T6.Slave = 0x0, T6.Master = &T7;
  T7.Key = 7, T7.Peer = &T8, T7.Slave = &T5, T7.Master = &T9;
  T8.Key = 8, T8.Peer = 0x0, T8.Slave = 0x0, T8.Master = &T9;
  T9.Key = 9, T9.Peer = 0x0, T9.Slave = &T4, T9.Master = 0x0;

  V.clear(); POTR(&T9, V);
  cout << "Recursive post-order-traversal says "; for (auto it = V.begin(); it != V.end(); ++it) cout << *it << " "; cout << endl;
  V.clear(); POTI(&T9, V);
  cout << "Iterative post-order-traversal says "; for (auto it = V.begin(); it != V.end(); ++it) cout << *it << " "; cout << endl;

  vector<int> A {1, 2, 3, 4, 5, 6, 7, 8, 9}, B {9, 4, 3, 1, 2, 7, 5, 6, 8}; DEBUG = 0;
  Tree *R = Resurrect(pair<vector<int>::iterator, vector<int>::iterator>(A.begin(), A.begin() + (A.size() - 1)), pair<vector<int>::iterator, vector<int>::iterator>(B.begin(), B.begin() + (B.size() - 1)));
  V.clear(); POTR(R, V); cout << "Resurrected "; for (auto it = V.begin(); it != V.end(); ++it) cout << *it << " "; cout << endl;

  return 0;
}





/* eof */
