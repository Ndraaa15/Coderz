package my.id.cupcakez.coderz.user;

public record Address(
        String street,
        String suite,
        String city,
        String zipcode,
        Geo geo
) {
}
