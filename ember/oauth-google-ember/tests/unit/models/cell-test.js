import { moduleForModel, test } from 'ember-qunit';
import Ember from 'ember';

moduleForModel('cell', 'Unit | Model | cell', {
  needs: ['model:workbook','model:worksheet']
});

test('belongs to a workbook', function(assert) {
	const Cell = this.store().modelFor('cell');
  const relationship = Ember.get(Cell, 'relationshipsByName').get('workbook');
  assert.equal(relationship.key, 'workbook', 'has relationship with workbook');
  assert.equal(relationship.kind, 'belongsTo', 'kind of relationship is belongsTo');
});

test('belongs to a worksheet', function(assert) {
	const Cell = this.store().modelFor('cell');
  const relationship = Ember.get(Cell, 'relationshipsByName').get('worksheet');
  assert.equal(relationship.key, 'worksheet', 'has relationship with worksheet');
  assert.equal(relationship.kind, 'belongsTo', 'kind of relationship is belongsTo');
});

test('has a row number', function(assert) {
  const Cell = this.store().modelFor('cell');
  const row = Ember.get(Cell, 'attributes').get('row');
  assert.equal(row.type, 'number', 'type that row is');
});

test('has a column number', function(assert) {
  const Cell = this.store().modelFor('cell');
  const column = Ember.get(Cell, 'attributes').get('column');
  assert.equal(column.type, 'number', 'type that column is');
});

test('has a value', function(assert) {
	const Cell = this.store().modelFor('cell');
  const value = Ember.get(Cell, 'attributes').get('value');
  assert.equal(value.type, 'string', 'type that value is');
});