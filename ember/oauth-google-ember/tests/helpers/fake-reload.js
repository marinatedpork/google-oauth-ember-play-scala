export default function fakeReload(obj) {
  return function() {
    return {
      then(fn) {
        return fn(obj);
      }
    };
  };
}
