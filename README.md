# Security Awareness Workshop

This **vulnerable** application aims to teach a small amount of Secure Coding to Java Developers.
It's a Spring Boot application using Spring Security.

The goal is to give developers a small improvement in Security Awareness by showing a few things that can go wrong, including how to fix and prevent them.
Each package is its own subject, containing a RestController to interact with it and is accompanied by Unit Tests.
Sometimes there are missing Unit Tests which the developer has to create him/herself.

The aim for this workshop is to fit within 4 hours, aiming at junior/medior Java Developers, including a presentation given by Scyon which isn't public. This presentation also contains the topics of SQL Injection and Cross-Site Scripting, but don't apply to this application.
The topics are inspired by the [OWASP Top 10](https://owasp.org/www-project-top-ten/) and the [Common Weakness Enumeration Top 25](https://cwe.mitre.org/top25/archive/2022/2022_cwe_top25.html).

There's a branch "solution" which contains solutions for both the implementation and unit tests.
However, to make full use of the learning potential we of course advise you not to peek until you're finished ;-).

## Overview of content

### Broken Access Control
**Package:** authentication

An example of the "Deny by default" principle.
The endpoint `/authenticated/ping` was implemented first and authentication was added. Later on, `/authenticated/pong` was added but somebody forgot to add authentication. How can we improve this? Make sure to add unit tests as well.

Additionally, the endpoint `/authenticated/apiKey` was added including authentication using an API key (and the Basic authentication from the other authenticated endpoints, because we can). However, there have been reports that the filter for this is vulnerable to bypass authentication. Find the vulnerability, add a unit test for it, and fix the vulnerability (and unit test).

### Path Traversal
**Package:** fileinclusion

An example of the Path Traversal vulnerability.
Write unit tests to simulate a Path Traversal attack and fix the vulnerable code to pass your unit test.

### Validation
**Package:** validation

An example to show why whitelist validation is way more powerful than blacklist validation.
Convert the validator to whitelist validation.
