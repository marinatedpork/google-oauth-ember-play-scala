import Ember from 'ember';

const { Component } = Ember;

export default Component.extend({
	tagName: 'th',
	didInsertElement() {
		this.$().attr('scope', 'row');
	}
});
