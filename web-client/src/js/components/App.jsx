import * as React from 'react';
import {useState} from "react";



export const App = () => {
    const [products, setProducts] = useState([])

    const getProducts = () => {
        fetch("http://localhost:8080/products")
            .then(result => result.json())
            .then(setProducts)
    }

    return <div>
        <button
            onClick={getProducts}
        >Get products!</button>

        {products.map(product =>
            <p>{ `${product.name} (${product.price})`}</p>
            )
        }

    </div>
}