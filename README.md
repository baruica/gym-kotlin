# The gym

## Requirements

- I want to create monthly or yearly subscription plans, with a base price
- Yearly subscriptions get 30% off
- Students that subscribe to any plan get 20% off
- I want to see my turnover for a given month, based on the ongoing subscriptions
- I want to change the price of a plan
- Subscriptions are renewed automatically
- A welcome email is sent to new members
- After 3 years of membership, members receive a "thank you" email

## Choices

- DDD tactical patterns
  - *Aggregate* ([read](https://vaughnvernon.co/?p=838))
  - *Entity* ([read](http://thepaulrayner.com/blog/aggregates-and-entities-in-domain-driven-design/))
  - *Value Object* ([read 1](http://verraes.net/2016/02/type-safety-and-money/), [read 2](https://matthiasnoback.nl/2018/03/modelling-quanities-an-exercise-in-designing-value-objects/))
  - *Domain Event* ([read](http://verraes.net/2014/11/domain-events/))
  - *Repository*
- *Aggregate* ids are provided by their *repository* ([read](https://matthiasnoback.nl/2018/05/when-and-where-to-determine-the-id-of-an-entity/))
- *Aggregate* ids have their own types ([read](https://buildplease.com/pages/vo-ids/))
- *Domain events* are put aside during the aggregate's execution, then "collected" and published by the use case ([read](https://lostechies.com/jimmybogard/2014/05/13/a-better-domain-events-pattern/))
- avoid *get/set* prefixes ([read](https://blog.pragmatists.com/refactoring-from-anemic-model-to-ddd-880d3dd3d45f))

## Links

- [Arnaud Lemaire's "DDD & CQRS" talk](https://www.youtube.com/watch?v=qBLtZN3p3FU)
  - [Slides](https://speakerdeck.com/lilobase/ddd-and-cqrs-php-tour-2018)
