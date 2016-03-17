import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';
import Ember from 'ember';

moduleForComponent('worksheet-nav', 'Integration | Component | worksheet nav', {
  integration: true
});

const TITLE = 'Taxes 2015';

test('renders a link for each worksheet', function(assert) {
  let worksheet = Ember.Object.create({id: 1, title: TITLE});
  let worksheets = [worksheet, worksheet];
  this.set('worksheets', worksheets);
  this.render(hbs`{{worksheet-nav worksheets=worksheets}}`);
  assert.equal(this.$('a').length, 2);
});

test('renders worksheet title as link text', function(assert) {
  let worksheet = Ember.Object.create({id: 1, title: TITLE});
  let worksheets = [worksheet];
  this.set('worksheets', worksheets);
  this.render(hbs`{{worksheet-nav worksheets=worksheets}}`);
  assert.equal(this.$().first('a').text().trim(), TITLE);
});
