import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';
import Ember from 'ember';

moduleForComponent('worksheet-table-row-cell', 'Integration | Component | worksheet table row cell', {
  integration: true
});

const CELL_VALUE = 'Hello World';

test('it renders the cell value', function(assert) {
  let cell = Ember.Object.create({value: CELL_VALUE});
  this.set('cell', cell);
  this.render(hbs`{{worksheet-table-row-cell cell=cell}}`);
  assert.equal(this.$().text().trim(), CELL_VALUE);
});
