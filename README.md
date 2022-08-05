# AutoKomis
Text game simulating the work of a car dealership owner.

# CHALLENGE

## Write a program in the form of a text game simulating the work of a car dealership owner.

You start out with a certain amount of cash, you don't have any cars, but you have access to a dozen or so cars that you can buy. The database of cars to be bought should be randomly generated. You can prepare a larger vehicle database in advance, some of which will be randomly selected, or write a vehicle generator. The base is replenished with new cars after each purchase.
### Each car has a specific:
  -	value,
  - brand,
  -	course,
  -	color,
  - segment (premium / standard / budget)
  - and a set of 5 items that may be in working order or require repair.

### Each component costs money to repair, but it also adds value to the car.
  - Brakes - increase the value of the car by 10%
  - Suspension - increases the value of the car by 20%
  - Engine - increases the value of the car by 100%
  - Body - increases the value of the car by 50%
  - Gearbox - 50%

Additionally, some of the cars are delivery vans for which the size of the cargo space is important.

### Each of the elements can be repaired by 3 different mechanics.
  - Janusz - has the most expensive prices but 100% guarantee
  - Marian - he takes much less than Janusz, but you have a 10% chance that he will not be able to repair the car and that Janusz's intervention will be necessary
  - Adrian - it's the cheapest but you have a 20% chance he won't fix it and a 2% chance he'll break something else while repairing

Repair costs should depend on the make of the vehicle and the part to be repaired.

Additionally, you must wash each car and pay 2% value tax when you buy it and when you sell it.

There is a database of potential customers in the game. Initially, it is a few people, but you can spend money on a marketing campaign to increase the amount. Completing one successful transaction generates 2 leads at no extra cost. An ad in a local newspaper brings in a random group of a few new customers, but it costs money. Or, you can invest in internet advertising, which is cheaper than a newspaper, but brings you one new potential customer.

Some customers are interested in passenger cars and other vans. Each customer has a certain amount of cash, two brands of vehicles that interest him, a small group will accept the purchase of a damaged vehicle, some will agree to a faulty suspension, but most of them will only buy a fully functional car. You can prepare a larger list of potential customers in advance or write a generator.
### Options available to the user
  - Browse the database of cars to buy
  - buy a car
  - browse the database of owned cars
  - repair the car
  - Browse through potential customers
  - sell a car for a certain price to a potential customer
  - buy an ad
  - check your account balance
  - check transaction history
  - Check the repair history of each vehicle
  - Check the total repair and washing costs for each of your vehicles
  
 
### Object of the game
Double your account balance in the fewest number of moves. One move is to buy a car / sell a car / fix one element / add one ad. Viewing your account balance, transaction history, customer databases, vehicles and vehicles available for purchase does not imply traffic consumption.
