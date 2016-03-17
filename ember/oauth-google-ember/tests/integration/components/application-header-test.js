import { moduleForComponent, test } from 'ember-qunit';
import hbs from 'htmlbars-inline-precompile';

moduleForComponent('application-header', 'Integration | Component | application header', {
  integration: true
});

const TITLE = 'Taxes 2015';

test('it renders a title if showTitle is true', function(assert) {
  this.render(hbs`{{application-header title='Taxes 2015' showTitle=true}}`);
  assert.equal(this.$().text().trim(), TITLE);
});

test('it does not render a title if showTitle is false', function(assert) {
  this.render(hbs`{{application-header title='Taxes 2015' showTitle=false}}`);
  assert.equal(this.$().text().trim(), '');
});