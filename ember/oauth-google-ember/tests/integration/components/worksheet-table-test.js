import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';
import Ember from 'ember';

moduleForComponent('worksheet-table', 'Integration | Component | worksheet table', {
  integration: true
});

test('renders rows as table row tags', function(assert) {
  let cell = Ember.Object.create({value: 'Hello World'});
  let rows = [[cell]];
  this.set('rows', rows);
  this.render(hbs`{{worksheet-table rows=rows}}`);
  assert.ok(this.$('tr').length);
});
