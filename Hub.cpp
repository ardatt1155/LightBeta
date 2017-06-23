
/*

Are you familiar with a PubSub pattern?
Define a PubSub pattern. What framework/library are you familiar with that uses this pattern.
Have you used a PubSub pattern in real? What about event-driven programming?
List the basic types required for this pattern. Job. Serf. Hub. Event.
What is the interface of a Hub?
Describe the simplest Job and Serf and Event you can think of.
Write out the types with classes/objects/structs and the interfaces.
Describe the private side of Hub. Let us do a list-based implementation.
Describe a simple demo function.
Code a single-threaded singleton implementation.
What is your space and time complexity?
Dispatch the Serf on different thread. What is time complexity now?
Make your singleton factory threadsafe.
How do you unsubscribe? Add an interface.
What are the various ways to garbage-collect? Explicit unsubscription and background purging.
Do a threadsafe background purging.
Infer that your code is free of deadlock. Assert any assumptions about the threading library.

*/

#include <cassert>
#include <iostream>
#include <map>
#include <memory>
#include <deque>
#include <string>
#include <thread>
#include <chrono>
using namespace std;

static const int DEBUG = 1;

class Job {
  public:
    string *name;
    Job(const char * name) {
      this->name = new string(name);
    }
};

class Serf {
  private:
    static atomic<int> led;
    int mark;

  public:
    Serf() {
      this->mark = 1 + led.fetch_add(1, memory_order_seq_cst);
    }
    void run(shared_ptr<Job> j) {
      int timeout = 5;
      while (timeout && led.load(memory_order_seq_cst) != this->mark)
        this_thread::sleep_for(chrono::milliseconds(timeout--));
      led.fetch_sub(1, memory_order_seq_cst);
      cout << this << " consumes " << j.get()->name << " after " << timeout << " waits" << endl;
    }
};
atomic<int> Serf::led(0);


class Hub {
  private:
    static Hub* singleton;
    static mutex* fmx;
    static mutex* gmx;
    static void dispatch(deque<weak_ptr<Serf>> *serfs, shared_ptr<Job> w);
    static void clean(thread::id mid);
    map<string, deque<weak_ptr<Serf>> *> *kennel;
    Hub();
    Hub(const Hub &);
    Hub& operator=(const Hub &);

  public:
    static Hub* factory();
    void publish(string e, shared_ptr<Job> w);
    void subscribe(string e, shared_ptr<Serf> s);
    ~Hub();
};

Hub::Hub() {
  this->kennel = new map<string, deque<weak_ptr<Serf>> *>();
}

Hub::~Hub() {
  delete this->kennel;
}

Hub* Hub::singleton = NULL;
mutex* Hub::fmx = new mutex();
mutex* Hub::gmx = new mutex();

Hub* Hub::factory() {
  Hub::fmx->lock();
  if (Hub::singleton == NULL) thread(Hub::clean, this_thread::get_id()).detach();
  Hub::singleton = Hub::singleton == NULL ? new Hub() : Hub::singleton;
  Hub::fmx->unlock();
  return Hub::singleton;
}

void Hub::clean(thread::id mid) {
  thread::id tid = this_thread::get_id();
  assert(tid != mid);
  Hub* H = Hub::factory();
  while (1) {
    Hub::gmx->lock();
    int expirations = 0;
    for (auto it = H->kennel->begin(); it != H->kennel->end(); ++it)
      for (auto iter = it->second->begin(); iter != it->second->end(); )
        if (!(*iter).expired()) ++iter;
        else iter = it->second->erase(iter), expirations++;
    Hub::gmx->unlock();
    if (DEBUG) cout << "Garbage collected the Hub for " << expirations << endl;
    this_thread::sleep_for(chrono::seconds(2));
  }
}

void Hub::dispatch(deque<weak_ptr<Serf>> *serfs, shared_ptr<Job> w) {
  if (serfs == NULL) return;
  for (auto iter = serfs->begin(); iter != serfs->end(); ++iter) {
      shared_ptr<Serf> serf = (*iter).lock();
      if (serf) serf.get()->run(w);
  }
}

void Hub::subscribe(string e, shared_ptr<Serf> f) {
  Hub::gmx->lock();
  auto *serfs = this->kennel->find(e) == this->kennel->end() ? new deque<weak_ptr<Serf>>() : this->kennel->at(e);
  serfs->push_back(f);
  this->kennel->insert(make_pair(e, serfs));
  Hub::gmx->unlock();
}

void Hub::publish(string e, shared_ptr<Job> w) {
  Hub::gmx->lock();
  auto *serfs = this->kennel->find(e) == this->kennel->end() ? NULL : this->kennel->at(e);
  auto *clone = new deque<weak_ptr<Serf>>(serfs->begin(), serfs->end());
  Hub::gmx->unlock();
  thread t(Hub::dispatch, clone, w);
  t.detach();
}



int main() {
  Hub* h = Hub::factory();
  int rounds = 6;
  while (--rounds) {
    cout << "Commencing round " << rounds << endl;
    {
      auto s1 = shared_ptr<Serf>(new Serf());
      h->subscribe(string("tick"), s1);
      auto j1 = shared_ptr<Job>(new Job("Here is a job"));
      h->publish(string("tick"), j1);
    }

    auto s2 = shared_ptr<Serf>(new Serf());
    h->subscribe(string("tick"), s2);
    auto j2 = shared_ptr<Job>(new Job("Here is a job"));
    h->publish(string("tick"), j2);

    {
      auto s3 = shared_ptr<Serf>(new Serf());
      h->subscribe(string("tick"), s3);
      auto j3= shared_ptr<Job>(new Job("Here is a job"));
      h->publish(string("tick"), j3);
      this_thread::sleep_for(chrono::seconds(2));
    }

    this_thread::sleep_for(chrono::seconds(2));
  }
  return 0;
}


/* eof */
