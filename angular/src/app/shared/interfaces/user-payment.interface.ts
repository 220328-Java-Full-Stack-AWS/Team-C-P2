export interface UserPayment {
  id?: number | null,
  network: string,
  issuer?: string | null,
  cardNumber: number,
  securityCode: number,
  expirationDate: string
}
