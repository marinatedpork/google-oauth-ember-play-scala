import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';
import Ember from 'ember';

moduleForComponent('worksheet-table-row', 'Integration | Component | worksheet table row', {
  integration: true
});

const CELL_VALUE = 'Hello World';

test('it renders the cell value', function(assert) {
  let worksheet = Ember.Object.create({value: CELL_VALUE});
  let row = [worksheet];
  this.set('row', row);
  this.render(hbs`{{worksheet-table-row row=row}}`);
  assert.equal(this.$().first('td').text().trim(), CELL_VALUE);
});