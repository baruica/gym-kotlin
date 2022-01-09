# The gym

## Requirements

- Owners can create monthly or yearly subscription plans, with a base price
- Yearly subscriptions get 10% off
- Students that subscribe to any plan get 20% off
- Owners can see how much they make from ongoing subscriptions for a given month
- Owners can change the price of a plan
- Monthly subscriptions are renewed automatically
- A welcome email is sent to new members
- A summary of the new subscriptions is sent by email
- After the first 3 years of membership, we apply 5% off all their subscriptions and give them the good news with an email

## Choices

- DDD tactical patterns
  - *Aggregate* ([read](https://vaughnvernon.co/?p=838))
  - *Entity* ([read](http://thepaulrayner.com/blog/aggregates-and-entities-in-domain-driven-design/))
  - *Value Object* ([read](https://dev.to/flbenz/kotlin-and-domain-driven-design-value-objects-4m32))
  - *Domain Event* ([read](http://verraes.net/2014/11/domain-events/))
  - *Repository*
- *Aggregate* ids are provided by their *repository* ([read](https://matthiasnoback.nl/2018/05/when-and-where-to-determine-the-id-of-an-entity/))
- *Aggregate* ids have their own types ([read](https://buildplease.com/pages/vo-ids/))
  - use ULIDs instead of UUIDs ([read](https://sudhir.io/uuids-ulids))
- *Aggregate roots* are constructed through factory methods ([read](https://buildplease.com/pages/constructing-aggregates/))
- *Domain events* are returned along with the aggregate's state in a dedicated construct (like a tuple)
- all objects are closed for extension by default ([read](https://ocramius.github.io/blog/when-to-declare-classes-final/))
- avoid *get/set* prefixes ([read](https://blog.pragmatists.com/refactoring-from-anemic-model-to-ddd-880d3dd3d45f))
- exception messages are hidden in the exception classes ([read](http://rosstuck.com/formatting-exception-messages))
