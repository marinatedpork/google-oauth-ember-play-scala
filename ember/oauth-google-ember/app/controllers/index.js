import Ember from 'ember';

const { Controller, inject, computed } = Ember;

export default Controller.extend({
  applicationController: inject.controller('application'),
  isError:               computed.reads('applicationController.isError')
});
