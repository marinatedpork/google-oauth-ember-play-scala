import Ember from 'ember';

/**
 * Loads the model and shows an animated spinner while it waits.
 * Catches errors and sends to index.
 */

export default Ember.Route.extend({
  model() {
    return this.store.findAll('workbook', {reload: true});
  },
  actions: {
    loading(transition) {
      this.controllerFor('application').set('showTitle', false);
      let controller = this.controllerFor('workbook');
      controller.set('currentlyLoading', true);
      transition.promise.finally(() => {
        controller.set('currentlyLoading', false);
      });
    },
    error() {
      return this.transitionTo('index', { queryParams: { error: 'true' }});
    }
  }
});
