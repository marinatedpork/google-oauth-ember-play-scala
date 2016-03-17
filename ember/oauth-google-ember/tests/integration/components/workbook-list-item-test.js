import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';
import Ember from 'ember';

moduleForComponent('workbook-list-item', 'Integration | Component | workbook list item', {
  integration: true
});

const TITLE = 'Taxes 2015';

test('it renders the workbook title', function(assert) {
  let workbook = Ember.Object.create({id: 1, title: TITLE});
  this.set('workbook', workbook);
  this.render(hbs`{{workbook-list-item workbook=workbook}}`);
  assert.equal(this.$().text().trim(), TITLE);
});
