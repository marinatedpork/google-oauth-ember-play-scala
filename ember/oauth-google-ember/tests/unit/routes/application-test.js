import { moduleFor, test } from 'ember-qunit';

moduleFor('route:application', 'Unit | Route | application', {
});

test('initializes WOW on beforeModel', function(assert) {
  let route = this.subject();
  let wow = route.get('beforeModel')();
  assert.equal(wow.constructor.name, 'Array', 'beforeModel returns Array, the return type on WOW.init()');
});
