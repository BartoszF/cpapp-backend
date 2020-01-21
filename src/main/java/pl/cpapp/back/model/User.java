package pl.cpapp.back.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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

    public List<Conversation> getConversations() {
        List<Conversation> joined = new ArrayList<>(conversationsA);
        joined.addAll(conversationsB);

        joined.sort(Comparator.comparing(Conversation::getModificationDate));

        return joined;
    }
}
