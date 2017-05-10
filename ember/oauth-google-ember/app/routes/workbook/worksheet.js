import Ember from 'ember';

/**
 * Refreshes the model and shows an animated spinner while it waits.
 * Catches errors and sends to index.
 */

export default Ember.Route.extend({
  model(params) {
    return this.store.findRecord('worksheet', params.worksheet_id, {reload: true});
  },
  setupController(controller, model) {
    controller.set('currentlyLoading', true);
    model.reload().then( (sheet) => {
      controller.set('currentlyLoading', false);
      controller.set('model', sheet);
    });
  },
  actions: {
    error() {
      this.transitionTo('index', { queryParams: { error: 'true' }});
    }
  }
});
