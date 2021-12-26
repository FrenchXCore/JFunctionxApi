<!--
parent:
  order: false
-->

<div align="center">
  <h1>FunctionX ($FX) - FXCore REST API library for Java</h1>
</div>
<p align="center">
  <img src="https://github.com/FrenchXCore/JavaFxCoreRestAPI/blob/main/resources/logo-functionx-730x482.jpeg" />
</p>

## Introduction

FunctionX is a decentralized ecosystem relying on the Cosmos ecosystem.

FXCore is the main blockchain ($FX) upon which is relying the PundiX ($PUNDIX) on-chain payment system.

This Java API allows you to interface to a FunctionX mainnet/testnet node in order to use its RPC REST interface (usually on port 1317).

## Quick start

<p>First, you need to make sure the node's API is enabled.</p>
<p>In order to do that, go into the node's configuration directory, and modify the [app] section so that enabled=true, and restart your node.</p>
<br/>
<p>Have a look at the 'Example.java' class to understand how to use it.</p>
<p>The RestService class includes all the methods available for query on any Cosmos-based RPC interface.</p>

## Useful to know

Some latest modules of the Cosmos SDK are not yet implemented into the FXCore mainnet or testnet nodes (authz, nft, etc.) but methods are already provided.
When used, they will return an error.

## To be achieved

Implementation of "gov(v1beta2)"

## Testing

This library is not yet fully tested. So please be indulgent.

The following queries were tested successfully.

### Module 'slashing'
- QuerySigningInfosResponse

### Module 'staking'
- QueryValidatorDelegationsResponse

Testing of each

Have fun !
