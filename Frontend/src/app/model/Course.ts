export class Course {
    private _name: string;
    private _description: string;
    private _price: number;

    constructor(name: string, description: string, price: number) {
      this._name = name;
      this._description = description;
      this._price = price;
    }

    public get name() {
      return this._name;
    }

    public get description() {
      return this._description;
    }

    public get price() {
      return this._price;
    }

    public set name(name: string) {
      this._name = name;
    }

    public set description(description: string) {
      this._description = description;
    }

    public set price(price: number) {
      this._price = price;
    }
}

