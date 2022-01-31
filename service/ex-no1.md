#Interfaces 
###Advantages 
I think the most important con about coding against interfaces is interface helps programmer to implement TDD technique. 
Secondly using interfaces could help in situations when new implementation of service need to be done, in that case new 
implementation doesnt destroy all previous logic of system, because its just replaces old code of service, that also means service still has same inputs and outputs.
In the end it makes easier to define service or api methods, so that all implementations provide the expected methods. 

###Disadvantages 
The most obvious fact is interface adds boilerplate code and makes system to have 2x much more classes.  

##Interfaces with only one implementation 
We should use interfaces even with only one implementation due to future extension of code and of course advantage of using interface is that code becomes closed for modification.
