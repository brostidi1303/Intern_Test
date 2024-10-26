const array = [3, 7, 1, 2, 6, 4];

function missingNumber_usingSum(array) {
  let arraySum = array.reduce((a, b) => a + b, 0);

  let expect = 0;
  const n = array.length + 1; // has to +1 to the size of array
  for (let i = 1; i <= n; i++) expect += i;

  return expect - arraySum;
  // time complexity: O(n), space complexity: O(1)
}

function missingNumber_naiveApproach(array) {
  let n = array.length;
  let tempArray = new Array(n + 1).fill(-1);

  for (let i = 0; i < n; i++) {
    tempArray[array[i]] = array[i];
  }

  for (let i = 1; i < tempArray.length; i++) {
    if (tempArray[i] === -1) return i;
  }

  return -1; //default value: no missing
  // time complexity: O(n), space complexity O(n)
}

console.log(missingNumber_usingSum(array)); //expected 5
console.log(missingNumber_naiveApproach(array)); //expected 5
