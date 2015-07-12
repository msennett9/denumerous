## Introduction ##

This document will provide answers to frequently asked questions about Denumerous - or at least the questions we think it's likely someone might ask!

## General ##
**Q: There are lots of tools to generate test combinations.  Why start another?**

Yes there are a lot of tools in this space.  Some of them are free (as in Free Beer) and some of them are commercial.  But there doesn't seem to be a open-source alternative available with the following characteristics:

  * implemented in Java
  * command-line and web interfaces
  * core library that can be used in other tools such as test harnesses and test management tools
  * framework for easily adding new generation algorithms.

**Q: What types of test generation will Denumerous currently do?**

Currently, Denumerous only generates 2-way or pairwise combinations.  We hope to add more functionality soon.  We would welcome your contributions if you have the ability to implement more generation algorithms!

**Q: Do you think you can create better test generation algorithms than the ones in existing tools?**

No.  Some of the available tools are implemented by people who are leading researchers in this field.  However, the best implementations have not been released as open source.  We hope that the Denumerous project might encourage some talented people to contribute implementations.  And, of course, contributors to this project can implement alogrithms that have been published.

**Q: What is the need for a web-based interface?**

Some organizations don't allow people to download client tools, so web-based implementations are convenient for people who work at these places.  Also, since test combination generation can be computationally expensive, an architecture that supports server-side generation may also be a good idea, and provide opportunities for caching and pre-calculating results.

**Q: What are the plans for Denumerous?**

See the DevelopmentPlan.

## Technical ##

**Q: The DenumerousWebUI project doesn't seem to build in Eclipse. Why?**

You need to build run the build for denumerous.jar and then copy it to the WEB-INF/lib directory.  (Yes, the build should do that automatically, but it doesn't just yet.)

## Miscellaneous ##

**Q: Where did the name _Denumerous_ come from?**

It was an attempt to be clever - as in, when the test cases are too numerous, use "denumerous."
