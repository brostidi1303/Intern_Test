// question 2.1: Product Inventory management
class Product {
  constructor(name, price, quantity) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  calculateTotalPrice() {
    return this.price * this.quantity;
  }
}

class Inventory {
  products = {
    laptop: new Product("laptop", 999.99, 5),
    smartphone: new Product("smartphone", 499.99, 10),
    tablet: new Product("tablet", 299.99, 0),
    smartwatch: new Product("smartwatch", 199.99, 3),
  };

  constructor(products) {
    this.products = products;
  }

  calcuateTotalPrice() {
    let total = 0;
    for (const key in this.products)
      total += this.products[key].calculateTotalPrice();
    return total;
  }

  mostExpensiveProduct() {
    let max = 0;
    let mostExpensiveProduct;
    for (const key in this.products) {
      if (this.products[key].price > max) {
        max = this.products[key].price;
        mostExpensiveProduct = this.products[key];
      }
    }
    return mostExpensiveProduct;
  }

  checkProduct(name) {
    for (const key in this.products) {
      if (this.products[key].name === name) {
        return true;
      }
    }
    return false;
  }

  sort(option = "ASC" | "DESC", orderBy = "price" | "quanitity") {
    const factor = option === "ASC" ? 1 : -1;

    const sortedArray = Object.entries(this.products).sort(
      ([, a], [, b]) => factor * (a[orderBy] - b[orderBy])
    );

    this.products = Object.fromEntries(sortedArray);
  }
}
