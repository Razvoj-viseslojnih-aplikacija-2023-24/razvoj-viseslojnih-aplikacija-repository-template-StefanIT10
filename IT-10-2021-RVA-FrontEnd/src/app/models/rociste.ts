import { Predmet } from "./predmet";
import { Ucesnik } from "./ucesnik";

export class Rociste{
    id!: number;
    datumRocista!: Date;
    sudnica!: string;
    ucesnik!: Ucesnik;
    predmet!: Predmet;
}