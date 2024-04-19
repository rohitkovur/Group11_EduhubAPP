import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Stripe } from '@stripe/stripe-js';

declare var StripeCheckout: any; // Declare StripeCheckout globally

@Injectable({
    providedIn: 'root'
})
export class StripeService {
    stripePromise: Promise<Stripe>;

    constructor() {
        this.stripePromise = this.loadStripe();
    }

    private loadStripe(): Promise<Stripe> {
        return new Promise((resolve, reject) => {
            const script = document.createElement('script');
            script.src = 'https://js.stripe.com/v3/';
            script.onload = () => {
                resolve(window['Stripe'](environment.stripeKey)); // Initialize Stripe after script is loaded
            };
            script.onerror = (error) => {
                reject(error);
            };
            document.body.appendChild(script);
        });
    }

    async checkout(amount: number, courseId: string): Promise<void> {
        const stripe = await this.stripePromise;
        const { error } = await stripe.redirectToCheckout({
            mode: 'payment',
            lineItems: [{ price: 'price_1P5b3e2MaWGnQjIkh1Nmt1u7', quantity: 1 }], // Replace with your price ID
            successUrl: window.location.origin + `/success/${courseId}`,
            cancelUrl: window.location.origin + `/course/${courseId}`,
        });

        if (error) {
            console.error('Error:', error);
        }
    }
}
