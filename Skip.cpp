
/*
  A skip-list is a collection of lists. Each list contains keys in a sorted sequence.
  Longest list has all keys present. Shortest list has the fewest keys present.
  Shorter lists are like express lanes to traverse the list.
  A simple implementation promotes every alternate cells to the next shorter list. So every list is double the length of the next shortest list.
  Such an implementation is vulnerable to adversaries because they can keep updating only keys which are cloned a lot of times.
  Randomizing the skiplist involves flipping a coin to decide whether or not to promote a cell.
  If head, if cell is odd promote it else skip. If head, if cell is even promote only if previous cell was not promoted.
  Infer that the time-complexity is O(N) for building a skip-list from a sorted sequence of keys.
  Infer a O(log N) algorithm for insertion.
  Write classes for a skiplist. What is the space-complexity and what are the constant factors like?
  List skip-list operations - display, build (bulk insertion), search, peek, insert, delete.
  Display. Code a function that pretty prints a skiplist.
  Build. Build a skiplist given a sequence of keys. Just use your favorite sorting library.
  Search. Code a search by key.
  Peek. How can you implement fast lookup of a key given it's index in the sorted sequence. Code this.
  Insert. Implement an insertion operation. Verify this is O(log N). Hint, you have to flip coins in advance to determine which floor downwards to add clones of the key. Code this up.
  Delete. Implement this operation in O(log N) time. Code this up.
  Make your code threadsafe.

  Mark the hotspots of this list. Which elements are accessed most often by various operations.
  Think about scaling this skip-list to a skip-graph. List tenets of a distributed data-structure.
  Load balance the hotspots. Hint that s'th floor can have 2^s lists each of N/(2^(s+1)) cells.
  Now we have log(N) floors but a total of N lists and N copies of each cell.
  Observe that if s'th floor had 2^s lists and each list had N/(2^s) cells, space will be quadratic.
  What is the averag replication factor of each cell? Flaw is that some cells are not replicated at all.
  Adapt the build operation, search and peek operation. Show that they are all O(log N).
  How do you balance the load when a lot of read requests floow in?
  Adapt the insertion operation. Give me a single-threaded algorithm. Code this up. Show time-complexity and space-complexity.
  Adapt insertion to a multithreaded algorithm. Show me pseudo-code. Show time-complexity and space-complexity.
  Adapt the deletion operation. Give me code for a single-threaded algorithm. Show time-complexity and space-complexity.
*/

#include <algorithm>
#include <cassert>
#include <chrono>
#include <climits>
#include <iostream>
#include <mutex>
#include <random>
#include <thread>
using namespace std;

static const int DEBUG = 0;

class SkipCell {
  public:
    int key;
    SkipCell *up;
    SkipCell *down;
    SkipCell *left;
    SkipCell *right;

    int display();
    static SkipCell* factory();

  private:
    SkipCell();
    SkipCell(const SkipCell&);
    SkipCell& operator=(const SkipCell&);
};

SkipCell::SkipCell() {
  this->up = this->down = this->left = this->right = NULL;
}

int SkipCell::display() {
  int left = this->left ? this->left->key : 0;
  int right = this->right ? this->right->key : 0;
  int up = this->up ? this->up->key : 0;
  int down = this->down ? this->down->key : 0;
  cout << "C(" << this->key << ", " << up << ", " << down << ", " << left << ", " << right << ")";
  return 0;
}

SkipCell* SkipCell::factory() {
  return new SkipCell();
}

class SkipList {
  public:
    int size;
    int floor_count;
    SkipCell* floors;
    random_device* random;
    mutex* mtx;

    int build(vector<int> & A);
    int display();
    int search(int K);

    static SkipList* factory();

  private:
    SkipList();
    SkipList(const SkipList&);
    SkipList& operator=(const SkipList&);
};


SkipList* SkipList::factory() {
    return new SkipList();
}

SkipList::SkipList() {
  this->size = 0;
  this->floors = NULL;
  this->random = new random_device();
  this->mtx = new mutex();
  this->floor_count = 0;
}

int SkipList::display() {
  cout << "Displaying skyplist " << (int *)(this) << endl;
  cout << "Count of keys is " << this->size << " and count of floor is " << this->floor_count << endl;
  auto floor = this->floors->up;
  int floor_count = 0;
  while (1) {
    if (floor == NULL) break;
    floor_count++;
    auto cell = floor->left;
    cout << "F(" << floor_count << ") is [";
    while (1) {
      if (cell == NULL) break;
      cell->display(); cout << " ";
      cell = cell->right;
    }
    floor = floor->down;
    cout << "]" << endl;
  }
  return 0;
}

int SkipList::build(vector<int> & A)
{
  sort(A.begin(), A.end());
  assert(A[0] > 0);

  auto *floors = SkipCell::factory();
  for (int it = 0; it < A.size(); ++it) {
    auto *cell = SkipCell::factory();
    cell->key = A[it];
    if (floors->left == NULL) floors->left = cell;
    if (floors->right) floors->right->right = cell, cell->left = floors->right;
    floors->right = cell;
  }

  int floor_count, counter;
  floor_count = counter = (int)ceil(log(A.size()));
  while (--counter > 0) {
    auto upper_floor = SkipCell::factory();
    auto lower_floor = floors;
    floors = upper_floor;
    upper_floor->down = lower_floor;
    lower_floor->up = upper_floor;
    auto left_cell = lower_floor->left;
    while (1) {
      if (left_cell == NULL) break;
      int flip = (this->random->operator()()) % 2;
      if (flip) {
        auto *cell = SkipCell::factory();
        cell->down = left_cell;
        left_cell->up = cell;
        cell->key = left_cell->key;
        if (upper_floor->left == NULL) upper_floor->left = cell;
        if (upper_floor->right) upper_floor->right->right = cell, cell->left = upper_floor->right;
        upper_floor->right = cell;
      }
      left_cell = left_cell->right;
    }
  }

  this->mtx->lock();
  assert(this->size == 0);
  assert(this->floors == NULL);
  this->size = A.size();
  this->floors = SkipCell::factory();
  this->floors->up = floors;
  while (floors->down) floors = floors->down;
  this->floors->down = floors;
  this->floor_count = floor_count;
  this->mtx->unlock();
  return 0;
}

int SkipList::search(int K) {
  // Read operations are thread unsafe without MVCC
  if (this->size == 0) return 0;
  auto floor = this->floors->up;
  while (1) {
    if (floor == NULL) break;
    auto cell = floor->left;
    while (1) {
      if (cell == NULL) break;
      if (cell->key) return 1;
      cell = cell->right;
    }
  }
  return 0;
}





int main() {
  vector<int> A {9, 8, 7, 6, 5, 4, 3, 2, 1};
  int R = 10;

  while (R--) {
    cout << "Executing a round ... " << endl;
    auto S = SkipList::factory();
    S->build(A); S->display();
    for (auto it = A.begin(); it != A.end(); ++it) assert(S->search(*it));
    assert(S->search(0)); assert(S->search(INT_MAX));
    this_thread::sleep_for(chrono::seconds(1));
  }

  return 0;
}

/* eof */
