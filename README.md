# My Personal Project - A necessities manager
#### *Xutong Jiang*

## **Proposal**

Since the pandemic outbroke, people have been stuck in home for a long time. Sometimes, we may suddenly find that some 
necessities run out, and it may be hard to get them immediately, so a necessities' manager will be a big help to 
remind us current amount of necessities which should be typed in by users after each purchase, and what necessities will 
run out soon and thus make a purchase as soon as possible.

## **User Stories**

- As a user, I want to be able to add, remove (no longer needed) necessities.
- As a user, I want to be able to add necessities' amount after purchase.
- As a user, I want to be able to set an estimation of average daily usage of each necessity and reduce their amount 
every time I refresh.
- As a user, I want to be able to check what necessities will run out recently (like 7 days).
- As a user, I would like to know how many days later one certain necessity is going to run out.
- As a user, I want to be able to save my necessities list to a file.
- As a user, I want to be able to optionally load my necessities list from a file when the program starts.

##**Instructions for Grader**
- The first event is adding necessity to the list, you can generate it by click Make Change and then Add Necessity.
You can check the event by clicking Check Status in the main menu afterwards.
- The second event is removing necessity from the list, you can generate it by click Make Change and then 
Remove Necessity. You can check whether the event has been removed by clicking Check Status in the main menu afterwards.
- You can locate my visual component by clicking check status, there will be a picture of some necessities.
You can trigger my audio component by clicking Update List, then click Update, if the list is successfully updated, 
you will hear a notification background sound.
- You can save the state of the list by clicking the Save List in the main menu.
- The Necessities Manager will automatically load the list every time you run it, so you do not need to do anything to 
load the list.

##**Phase 4: Task 2**
- The returnRemainingDay method in Necessities class now throw a NotInListException. The exception will be caught in 
CheckStatusButton class. Both thrown and not thrown cases have been tested.

##**Phase 4: Task 3**
- Button 1 and its following buttons can be exacted to form a new class called CheckStatusButton which extends Button to
improve the cohesion of NecessitiesManager
- Button 5 and button8 can be exacted to form a new class called SaveListButton to improve the cohesion of NecessitiesManager.
**The two problems above has been solved.**
- Another four buttons and their containing buttons can also be extracted to form new classes.