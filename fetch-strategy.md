# Fetch and Cascade Strategy — AutoLoc

This document justifies the `fetch`, `cascade` and `orphanRemoval` choices for each association in the AutoLoc model.

## Summary table

| Association | Fetch | Cascade | Justification |
|---|---|---|---|
| Contrat → Paiement | LAZY | ALL + orphanRemoval | A payment only exists attached to its contract (composition). Deleting the contract deletes its payments, and removing a payment from the list deletes it in the database. |
| Agence → Vehicule | LAZY | None | A vehicle survives the deletion of its agency. The list of vehicles is only loaded on demand. |
| Agence → Employe | LAZY | None | An employee has an independent existence: deleting an agency must not automatically delete its employees. |
| Vehicule ↔ Equipement | LAZY | None | Equipment is shared between several vehicles: deleting a vehicle must not delete equipment used elsewhere. A `Set` avoids duplicates. |
| Client → Reservation | LAZY | PERSIST | A reservation created with a new client is saved along with it. No REMOVE, in order to keep the reservation history. |
| Reservation → Vehicule | LAZY | None | The vehicle exists independently of the reservations that refer to it. |
| Reservation ↔ Contrat | LAZY | ALL (Reservation side) | The contract derives from the reservation and follows its lifecycle (save, update, delete). |
| Vehicule → Maintenance | LAZY | PERSIST | A maintenance record is saved with its vehicle. No REMOVE: the maintenance history is kept. |

## General fetch choice

All associations use **LAZY**.

- By default, `@ManyToOne` and `@OneToOne` are EAGER, which loads a whole chain of related objects even when they are not needed.
- Collections (`@OneToMany`, `@ManyToMany`) are already LAZY by default: this behavior is kept.
- The risk of `LazyInitializationException` (accessing an association outside a transaction) will be handled with dedicated queries (Atelier 7).

## Owning side and inverse side

| Association | Owning side (foreign key) | Inverse side (`mappedBy`) |
|---|---|---|
| Contrat → Paiement | Paiement (`contrat_id_contrat`) | Contrat (`paiements`) |
| Agence → Vehicule | Vehicule (`id_agence`) | Agence (`vehicules`) |
| Agence → Employe | Employe (`id_agence`) | Agence (`employes`) |
| Vehicule ↔ Equipement | Vehicule (table `vehicule_equipement`) | Equipement (`vehicules`) |
| Client → Reservation | Reservation (`id_client`) | Client (`reservations`) |
| Reservation → Vehicule | Reservation (`id_vehicule`) | None (unidirectional) |
| Reservation ↔ Contrat | Contrat (`id_reservation`) | Reservation (`contrat`) |
| Vehicule → Maintenance | Maintenance (`id_vehicule`) | Vehicule (`maintenances`) |

## Good practices applied

- No `@Data` on entities: only targeted `@Getter` and `@Setter`, to avoid infinite loops in `toString`, `equals` and `hashCode` on bidirectional associations.
- Cascade is limited to composition cases or linked lifecycles; never REMOVE on entities that can live on their own.
- `Set` for the `@ManyToMany` relationship, to avoid duplicates and the performance issues that come with `List`.
