export default {
  promise: {
    finally(fn) {
      return fn();
    }
  }
};
