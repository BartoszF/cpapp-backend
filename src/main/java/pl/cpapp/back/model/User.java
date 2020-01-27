package pl.cpapp.back.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="number", unique = true)
    private String number;
    @Column(name="pin")
    private String pin;
    @Column(name="name")
    private String name;
    @Column(name="surename")
    private String surename;
    @Column(name="pseudo")
    private String pseudo;
    @Lob
    @Column(name="description")
    private String description;

    @OneToOne(mappedBy = "user")
    private Role role;

    @OneToMany(mappedBy = "userA")
    private List<Conversation> conversationsA;

    @OneToMany(mappedBy = "userB")
    private List<Conversation> conversationsB;

    @ManyToMany
    @JoinTable(name = "contacts",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private List<User> contacts;

    @ManyToMany
    @JoinTable(name = "contacts",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> contactOf;

    public List<Conversation> getConversations() {
        List<Conversation> joined = new ArrayList<>(conversationsA);
        joined.addAll(conversationsB);

        joined.sort(Comparator.comparing(Conversation::getModificationDate));

        return joined;
    }
}
