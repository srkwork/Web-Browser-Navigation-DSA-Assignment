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

#### CURRENT
#### <currentPage or null>
#### BACK
#### <back stack elements>
#### FORWARD
#### <forward stack elements>
#### HISTORY
#### <history queue elements>