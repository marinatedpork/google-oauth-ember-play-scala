import { moduleForModel, test } from 'ember-qunit';
import Ember from 'ember';


moduleForModel('worksheet', 'Unit | Model | worksheet', {
  needs: ['model:cell','model:workbook']
});

test('has many cells', function(assert) {
	const Worksheet = this.store().modelFor('worksheet');
  const relationship = Ember.get(Worksheet, 'relationshipsByName').get('cells');
  assert.equal(relationship.key, 'cells', 'has relationship with cells');
  assert.equal(relationship.kind, 'hasMany', 'kind of relationship is hasMany');
});

test('belongs to a workbook', function(assert) {
	const Worksheet = this.store().modelFor('worksheet');
  const relationship = Ember.get(Worksheet, 'relationshipsByName').get('workbook');
  assert.equal(relationship.key, 'workbook', 'has relationship with workbook');
  assert.equal(relationship.kind, 'belongsTo', 'kind of relationship is belongsTo');
});

test('has a title', function(assert) {
  const Worksheet = this.store().modelFor('worksheet');
  const title = Ember.get(Worksheet, 'attributes').get('title');
  assert.equal(title.type, 'string', 'type that title is');
});
