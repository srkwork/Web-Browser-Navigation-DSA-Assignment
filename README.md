# README/Report --- CS3345 Assignment 01: Web Browser Navigation System

## Student Information
* Name: Saaketh Katikareddy
* NetID: srk230016
* Section: 005


## 1. Project Overview
#### This project implements a simplified web browser navigation system in Java. The system mimics real-world browsing by supporting:
* Visiting websites
* Navigating backward and forward through pages
* Viewing and clearing browsing history
* Saving the session to a file when closing
* Restoring the last session on startup


## 2. Files Submitted
* Main.java --- driver program with interactive command loop
* BrowserNavigation.java --- core controller managing current page, navigation stacks, and history queue; handles file I/O for save/restore
* BrowserStack.java --- stack ADT implemented using BrowserLinkedList
* BrowserQueue.java --- queue ADT implemented using BrowserArrayList
* StackIterator.java --- iterator wrapper (included to match assignment structure)


## 3. Design & Implementation Choices
### 3.1 Data Structures Used
#### Back/Forward Navigation
* Implemented using two stacks:
    * backStack stores previously visited pages (LIFO)
    * forwardStack stores pages you can return to after going back (LIFO)

#### Browsing History
* Implemented using a queue (historyQueue) to preserve chronological order (FIFO)
* Queue uses a circular array (BrowserArrayList) for efficient enqueue/dequeue

### 3.2 Why
* Stacks naturally model browser navigation becuase the most recent page you can go back to should be visited first (LIFO)
* A queue naturally models browsing history because entries should appear in the order they were visited (FIFO)
* BrowserArrayList is a circular buffer to avoid shifting elements, and it resizes by doubling when full.

### 3.3 Session Save/Restore Format
#### closeBrowser() writes to session_data.txt in labeled sections:


```
CURRENT
<currentPage or null>
BACK
<back stack elements>
FORWARD
<forward stack elements>
HISTORY
<history queue elements>
```

#### restoreLastSession() parses this file using a "section mode" approach and rebuilds:
* currentPage
* backStack (pushed in reverse order to preserve stack top)
* forwardStack (pushed in reverse order)
* historyQueue (enqueued in forward order)


## 4. Testing Strategy & Test Cases
### Supported Commands:
* visit `&lt;url&gt;
* back
* forward
* history
* clear
* close
* exit

### Key Test Cases (Summary)
#### 1. Empty Start/Missing session file
* Commands: history, back, forward, exit
* Expected: no crashes; history prints "No browsing history available."

#### 2. Visit + History Order
* Commands: visit google.com, visit youtube.com, history
* Expected history: google.com, youtube.com (oldest to newest)

#### 3. Back/Forward Navigation
* Commands: visit google.com, visit youtube.com, visit github.com, back, back, forward
* Expected current page: youtube.com

#### 4. Forward Cleared After Visiting New Page
* Commands: visit google.com, visit youtube.com, visit github.com, back, visit twitter.com, forward
* Expected: forward does nothing after visiting x

#### 5. Clear History
* Commands: visit google.com, visit youtube.com, clear, history
* Expected: "No browsing history available."

#### 6. Close + Restore Session
* Run 1: visit pages, back/forward, close, exit
* Run 2: program auto-resotres previous current/back/forward/history


## 5. Time Complexity Analysis (Upper Bound Big-O)
### 5.1 BrowserLinkedList(used by stack)
* addFirst -> O(1)
* removeFirst -> O(1)
* peekFirst -> O(1)
* iterator traversal over n nodes -> O(n) total

### 5.2 BrowserStack (wraps BrowserLinkedList)
* push -> O(1)
* pop -> O(1)
* peek -> O(1)
* isEmpty -> O(1)
* clear -> O(1) (replaces list reference)
* iterating over n stack elements -> O(n) total

### 5.3 BrowserArrayList (circular queue + resizing)
###### Let n = current size.
* add (enqueue):
    * amortized O(1)
    * worse-case O(n) when resizing (copying n elements)
* remove (dequeue) -> O(1)
* peek -> O(1)
* clear -> O(1)
* iterator traversal over n elements -> O(n) total

### 5.4 BrowserQueue (wraps BrowserArrayList)
* enqueue:
    * amortized O(1)
    * worst-case O(n) during resize
* dequeue -> O(1)
* peek -> O(1)
* isEmpty -> O(1)
* clear -> O(1)
* size -> O(1)
* iterating over n elements -> O(n) total

### 5.5 BrowserNavigation
##### Let:
* b = size of backstack
* f = size of forwardStack
* h = size of historyQueue
* L = number of lines in the session files (b + f + h + constants)

* visitWebsite(url):
    * push to backStack + clear forward + enqueue history -> amortized O(1) (queue resize worst-case O(h))
* goBack() -> O(1)
* goForward() -> O(1)
* showHistory() -> O(h) (builds a string by iterating)
* clearHistory() -> O(1)
* closeBrowser -> O(b + f + h) (iterates all structures to write)
* restoreLastSession() -> O(L) to read + O(b + f + h) to rebuild
    * overall O(b + f + h)


## 6. Performance Enhancements
* used circular array in BrowserArrayList to ensure dequeue does not require shifting
* Used dynamic resizing (doubling capacity) to keep amortized enqueue cost near O(1).
* Used section-based parsing and temporary buffers to restore stacks without reversing navigation order.

