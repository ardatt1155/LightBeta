
/*
A rope is a data-structure composed of smaller strings that is used to efficiently store and manipulate a very long string.
*/

#include <iostream>
#include <cassert>
#include <cmath>
using namespace std;

static const int DEBUG = 1;
static void Trace(int line) {
  if (DEBUG > 0) cout << "[DEBUG] Executing " << line << endl;
}

static int counter = 0;

class Rope
{
  private:
    int uid;
    Rope *left;
    Rope *right;
    int length;
    char *text;

  public:
    Rope(Rope *left, Rope *right, int length, char *text) {
      counter++; this->uid = counter;
      this->left = left; this->right = right;
      this->length = length; this->text = text;
      if (this->length < 0) this->length = this->left->length + this->right->length;
      if (this->length == 0 && this->text == NULL) this->text = (char *)malloc(1 * sizeof(char)), this->text[0] = '\0';
      assert((this->left == NULL && this->right == NULL && this->text != NULL)
        || (this->left != NULL && this->right != NULL && this->text == NULL && this->length == this->left->length + this->right->length));
    }

    bool isLeaf() {
      if (this->left == NULL && this->right == NULL && this->text != NULL) return true;
      else if (this->left != NULL && this->right != NULL && this->text == NULL) return false;
      else assert(0);
    }

    Rope* show() {
      if (this->isLeaf())
        for (int iter = 0; iter < this->length; ++iter) cout << text[iter];
      else this->left->show(), this->right->show();
      return this;
    }

    Rope* debug(bool trickle) {
      cout << "[DEBUG] Rope = (this = " << this << ", this->uid = " << this->uid << ", this->left = " << (this->left ? this->left->uid : 0) << ", this->right = " << (this->right ? this->right->uid : 0) << ", this->length = " << this->length << ", this->isLeaf = " << this->isLeaf() << ")";
      if (!this->isLeaf() && trickle) cout << endl, this->left->debug(trickle), this->right->debug(trickle);
      else if (this->isLeaf()) for (int iter = 0, e = !!(cout << " text = "); iter < this->length || ((cout << endl) && false); ++iter) cout << text[iter];
      return this;
    }

    Rope* insert(int position, int length, char *text) {
      assert(position > -1 && length > -1 && !(this->length < position) && text != NULL);
      if (length < 1) return this;
      else if (this->isLeaf()) {
        char *first_begin = this->text, *second_begin = text, *third_begin = this->text + position;
        int first_length = position, second_length = length, third_length = this->length - position;
        Rope *left = this; left->left = NULL; left->right = NULL; left->length = first_length; left->text = first_begin;
        Rope *middle = new Rope(NULL, NULL, second_length, second_begin);
        Rope *right = new Rope(NULL, NULL, third_length, third_begin);
        Rope *bottom = new Rope(middle, right, -1, NULL);
        Rope *top = new Rope(left, bottom, -1, NULL);
        return top;
      } else {
        if (this->left->length > position) this->left = this->left->insert(position, length, text);
        else this->right = this->right->insert(position - this->left->length, length, text);
        this->length = this->left->length + this->right->length;
        return this;
      }
    }

    Rope* remove(int position, int length) {
      if (length < 1 || position < 0 || position > this->length) return this;
      else if (this->isLeaf()) {
        assert(this->length > position + length - 1);
        char *first_begin = this->text, *second_begin = this->text + position + length;
        int first_length = position, second_length = this->length - position - length;
        Rope *left = new Rope(NULL, NULL, first_length, first_begin);
        Rope *right = new Rope(NULL, NULL, second_length, second_begin);
        this->left = left; this->right = right; this->text = NULL; this->length = left->length + right->length;
      } else {
        int left_position = this->left->length > position ? position : this->left->length;
        int left_length = this->left->length > position ? min(this->left->length - position, length) : 0;
        int right_position = this->left->length < position ? position - this->left->length : 0;
        int right_length = length - left_length;
        this->left = this->left->remove(left_position, left_length);
        this->right = this->right->remove(right_position, right_length);
      }
      this->length = this->length - length;
      return this;
    }
};

int main()
{
  Trace(__LINE__);
  Rope *jumps = new Rope(NULL, NULL, 6, (char *)"jumps ");
  Rope *over = new Rope(NULL, NULL, 5, (char *)"over ");
  Rope *lion = new Rope(NULL, NULL, 5, (char *)"lion ");
  Rope *over_lion = new Rope(over, lion, -1, NULL);
  over_lion->show(); cout << endl;

  counter = 0;
  Rope *rope = new Rope(NULL, NULL, 0, NULL);
  rope = rope->insert(0, 2, (char *)"a ");
  rope = rope->insert(2, 6, (char *)"quick ");
  rope = rope->insert(8, 6, (char *)"brown ");
  rope->show(); cout << endl;
  rope->debug(true);
  rope = rope->remove(2, 8);
  rope->show(); cout << endl;
  return 0;
}
