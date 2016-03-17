import { moduleForModel, test } from 'ember-qunit';
import Ember from 'ember';

moduleForModel('workbook', 'Unit | Model | workbook', {
  needs: ['model:cell','model:worksheet']
});

test('has many worksheets', function(assert) {
	const Workbook = this.store().modelFor('workbook');
  const relationship = Ember.get(Workbook, 'relationshipsByName').get('worksheets');
  assert.equal(relationship.key, 'worksheets', 'has relationship with worksheets');
  assert.equal(relationship.kind, 'hasMany', 'kind of relationship is hasMany');
});

test('has a title', function(assert) {
  const Workbook = this.store().modelFor('workbook');
  const title = Ember.get(Workbook, 'attributes').get('title');
  assert.equal(title.type, 'string', 'type that title is');
});
